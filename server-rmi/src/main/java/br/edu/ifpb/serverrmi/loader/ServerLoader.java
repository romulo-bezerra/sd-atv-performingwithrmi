package br.edu.ifpb.serverrmi.loader;

import br.edu.ifpb.shared.ManagerUserId;
import br.edu.ifpb.shared.ManagerUserIdImpl;

import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerLoader {
    public static void main(String[] args) {
        try {
            ManagerUserId idGenerator = new ManagerUserIdImpl();
            ManagerUserId stub = (ManagerUserId) UnicastRemoteObject.exportObject(idGenerator,0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("RMI Application", stub);
            System.out.println("Servidor ligado");
        } catch (UnknownHostException e) {
            System.out.println("Host nao encontrado\n" + e.getMessage());
        } catch (RemoteException e) {
            System.out.println("Falha na conexão com o servidor" + e.getMessage());
        }
    }
}
