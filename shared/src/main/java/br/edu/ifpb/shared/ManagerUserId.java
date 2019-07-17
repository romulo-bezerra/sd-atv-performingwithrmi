package br.edu.ifpb.shared;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ManagerUserId extends Remote {
    Serializable createId() throws RemoteException;
    void verifyUserIdUsed(String userId) throws RemoteException;
}
