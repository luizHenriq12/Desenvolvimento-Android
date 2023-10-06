package com.alfaumuarama.aula_07.datasource;

import android.content.Context;
import android.database.Cursor;

import com.alfaumuarama.aula_07.moduls.Aluno;

import java.util.ArrayList;

public class TbAluno { public TbAluno(Context context) {
    //abrindo comando SQL para criar a tabela do Aluno
    BancoDados.getInstance().abrirBanco(context);

    //montando comando SQL para criar a tabela do Aluno
    String sql = "CREATE TABLE tbAluno (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nome TEXT," +
            "ra TEXT" +
            ")";
    //executando o comando SQL
    BancoDados.getInstance().executarSQL(sql);
}

    private void inserir(Aluno aluno) {
        //String sql = "INSERT INTO TbAluno(nome, ra) VALUES ('" +
        //      aluno.nome + "', '" + aluno.ra + "')";
        // BancoDados.getInstance().executarSQL(sql);

        BancoDados.getInstance().getDb().insert("TbAluno", null,aluno.toDados());
    }

    private void alterar(Aluno aluno) {
        BancoDados.getInstance().getDb().update(
                "TbAluno", //tabela
                aluno.toDados(), //novos dados
                "id = ?", //condicao WHARE
               new String[] { String.valueOf(aluno.id) } //valores dos parametros dp WHARE
        );
    }

    public void excluir(int id) {
        BancoDados.getInstance().getDb().delete("TbAluno",
                "id = ?", new String[] { String.valueOf(id) });
    }

    public void gravar(Aluno aluno) {

    //buscando o aluno, filtrando por ID
    ArrayList<Aluno> lista = buscar(aluno.id);

    if (lista.size() > 0) //se existir no banco de dados, altera o registro
        alterar(aluno);
    else //senao existir no banco de dados, insere o registro
        inserir(aluno);
    }

    public ArrayList<Aluno> buscarTodos() {
        return buscar(0); //passo zero para buscar todos os alunos
    }

    public Aluno buscarPorId(int id) {
        ArrayList<Aluno> lista = buscar(id);
        if (lista.size() > 0)
            return lista.get(0);
        return new Aluno();
    }

    private ArrayList<Aluno> buscar(int id) {
        String condicaoSQL = "";

        if (id > 0)
            condicaoSQL = "id = " + id;

        Cursor cursor = BancoDados.getInstance().getDb().query(
            "TbAluno", //tabela
            new String[] { "id", "nome", "ra" }, //colunas
            condicaoSQL, //condicao WHERE
            null, //parametros da condicao WHERE
            null, //grupoBy
            null, //having
            "nome", //orderBy
            null    //limit
        );
        return retornaLista(cursor);
    }

    private ArrayList<Aluno> retornaLista(Cursor cursor) {
        ArrayList<Aluno> lista = new ArrayList<>();
        //verificando se existe dados no cursor retornado pelo SELECT
        if (cursor.getCount() > 0) {
            cursor.moveToFirst(); //c

            //pega o indice da coluna retornado pelo SELECT
            int indiceCampoId = cursor.getColumnIndex("id");
            int indiceCampoNome = cursor.getColumnIndex("nome");
            int indiceCampoRa = cursor.getColumnIndex("ra");

            //percorrer todas as posicoes/registros do cursor
            for (int i = 0; i < cursor.getCount(); i++);
            Aluno aluno = new Aluno();
            aluno.id = cursor.getInt(indiceCampoId);
            aluno.nome = cursor.getString(indiceCampoNome);
            aluno.ra = cursor.getString(indiceCampoRa);

            //adicionando o aluno na lista de retorno
            lista.add(aluno);

            //mover o cursor para o proxima posicao da lista
            cursor.moveToNext();
        }

        return lista;
    }
}
