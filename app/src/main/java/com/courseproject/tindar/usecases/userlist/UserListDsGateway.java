package com.courseproject.tindar.usecases.userlist;

import java.util.ArrayList;

public interface UserListDsGateway {
    ArrayList<String> getAllOtherUserIds(String userId);
}
