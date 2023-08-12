package com.courseproject.tindar.usecases.conversations;

import java.util.ArrayList;
import java.util.List;


/**
 * Layer: Interface Adapter layer - where it connects use case (getting a list of active conversation
 * for the particular user to display on their page)
 *.
 * What:
 * This interface serves as a data source gateway to interact with the database,
 * where it would use this class to fetch the active conversations from the database.
 *.
 * Why does it exists:
 *.
 *
 */
public interface ConversationListDsGateway {
    // Retrieve active conversation data from the data source
    static List<ConversationDsResponseModel> getActiveConversationIds(String userId) {
        return null;
    }
}

