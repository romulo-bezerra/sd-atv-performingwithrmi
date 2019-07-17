package br.edu.ifpb.shared;

import br.edu.ifpb.serverrmi.factory.UserIDFactory;

import java.io.Serializable;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

public class ManagerUserIdImpl implements ManagerUserId {

    private UserIDFactory userIDFactory;

    public ManagerUserIdImpl() throws UnknownHostException { this.userIDFactory = new UserIDFactory(); }

    @Override
    public Serializable createId(){
        return userIDFactory.createId();
    }

    @Override
    public void verifyUserIdUsed(String userId) throws RemoteException {
        String useStateUserIdNegative = "NAO_USADO";
        String useStateUserIdPositive = "USADO";
        if (!userId.equalsIgnoreCase(""))
            System.out.println("UserId: " + userId + " está como " + useStateUserIdPositive);
        else System.out.println("UserId: " + userId + " está como " + useStateUserIdNegative);
    }
}
