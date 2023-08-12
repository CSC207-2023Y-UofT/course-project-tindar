package com.courseproject.tindar.usecases.userlist;

import java.util.ArrayList;

public interface UserListDsGateway {
    public ArrayList<String> getAllOtherUserIds(String userId);
}
