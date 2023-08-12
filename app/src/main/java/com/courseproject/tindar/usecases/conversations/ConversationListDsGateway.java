package com.courseproject.tindar.usecases.conversations;

import java.util.ArrayList;


/**
 * What:
 *
 * Why does it exists:
 *
 *
 */
public interface ConversationListDsGateway {
    // contains database manipulation function
    //

    ArrayList<ConversationDsResponseModel> getActiveConversationIds (String userId);

}
