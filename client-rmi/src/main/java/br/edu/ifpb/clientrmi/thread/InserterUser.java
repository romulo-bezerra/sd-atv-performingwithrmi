package br.edu.ifpb.clientrmi.thread;

import br.edu.ifpb.clientrmi.dao.UsuarioDao;
import br.edu.ifpb.clientrmi.domain.Usuario;
import br.edu.ifpb.shared.ManagerUserIdRemote;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Logger;

public class InserterUser implements Runnable {

    private final Logger log = Logger.getLogger(this.getClass().getName());
    private UsuarioDao usuarioDao;
    private Usuario usuario;
    private ArrayBlockingQueue<String> queueInsert;
    private ArrayBlockingQueue<String> queueUpdate;
    private ManagerUserIdRemote managerUserIdRemote;

    public InserterUser(Usuario usuario, ArrayBlockingQueue<String> queueInsert, ArrayBlockingQueue<String> queueUpdate) {
        this.usuario = usuario;
        this.queueInsert = queueInsert;
        this.queueUpdate = queueUpdate;
        this.usuarioDao = new UsuarioDao();
        this.managerUserIdRemote = new ManagerUserIdRemote();
        new Thread(this, "InserterUser").start();
    }

    @Override
    public void run() {
        insertUser();
    }

    private void insertUser() {
        try {
            String userIdInsertLiberado = queueInsert.take();
            usuarioDao.insert(usuario);

            String userId = "";
            if (usuarioDao.userIdUsed(usuario.getId())) userId = usuario.getId();

            //lança feedback sobre o estado de uso do id para o serverrmi
            managerUserIdRemote.verifyUserIdUsed(userId);

            queueUpdate.put(userIdInsertLiberado);
            log.info("Usuário inserido");
        } catch (InterruptedException e) {
            log.warning(Thread.currentThread().getName() + "Thread interrompida");
        }
    }

}
