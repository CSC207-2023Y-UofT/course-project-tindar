package com.courseproject.tindar.usecases.conversations;

import java.util.ArrayList;

public interface ConversationListDsGateway {
    // contains database manipulation function
    //

    ArrayList<ConversationDsResponseModel> getActiveConversationIds (String userId);

}
