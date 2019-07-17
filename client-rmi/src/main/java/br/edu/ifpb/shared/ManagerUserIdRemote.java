package br.edu.ifpb.shared;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ManagerUserIdRemote {

    private Registry registry;
    private ManagerUserId managerUserId;

    public ManagerUserIdRemote(){
        try {
            this.registry = LocateRegistry.getRegistry();
            this.managerUserId = (ManagerUserId) registry.lookup("ServerChat");
        } catch (RemoteException e) {
            System.out.println("Falha na conexão com o servidor" + e.getMessage());
        } catch (NotBoundException e) {
            System.out.println("Falha no processo de binding da interface" + e.getMessage());
        }
    }

    public Serializable getUserId() {
        try {
            return managerUserId.createId();
        } catch (RemoteException e) {
            System.out.println("Falha na conexão com o servidor" + e.getMessage());
        }
        return null;
    }

    public void verifyUserIdUsed(String userId){
        try {
            managerUserId.verifyUserIdUsed(userId);
        } catch (RemoteException e) {
            System.out.println("Falha na conexão com o servidor" + e.getMessage());
        }
    }

}
