package com.courseproject.tindar.usecases.conversations;

import java.util.ArrayList;
import java.util.List;


/**
 * Layer: Interface Adapter layer - connecting DB to interactor
 *.
 * What is it :
 * This interface serves as a data source gateway to interact with the database,
 * where it would use this class to retrieve conversations from the database.
 *.
 * Why does it exists:
 *.
 * It isolates the complexity of database manipulation and ensure that any changes in databse
 * does not affect business logic
 */
public interface ConversationListDsGateway {
    // Retrieve active conversation data from the data source
    static List<ConversationDsResponseModel> getActiveConversationIds(String userId) {
        return null;
    }
}

