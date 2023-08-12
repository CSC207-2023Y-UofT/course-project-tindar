package com.courseproject.tindar.usecases.conversations;

import java.util.ArrayList;
import java.util.List;

/**
 * Layer: Use cases - connects controller to database gateway
 *.
 * What is it :
 * This Interactor class mainly implements method define in the ConversationListInputBoundary
 * interface - get/update the conversations for a given user.
 *.
 * It calls DB function to manipulate database / method that does data saving.
 *     getting information from db to pass to controller, controller pass it to UI
 *     UI calls controller methods, and controller calls interactor
 *     UI display data
 *.
 * Why does it exists:
 * It exist to creating a clear boundary between the use case contract and its implementation
 *
 */


public class ConversationListInteractor implements ConversationListInputBoundary {

    private ConversationListDsGateway dsGateway;

    public ConversationListInteractor(ConversationListDsGateway dsGateway) {
        this.dsGateway = dsGateway;
    }

    /**
     * Retrieves a list of active conversations for the given user ID.
     *
     * @param userId The ID of the user whose active conversations are to be retrieved.
     * @return List of active conversations for the user.
     */
    @Override
    public ArrayList<ConversationResponseModel> getActiveConversations(String userId) {
        // Fetch active conversations from the data source gateway
        List<ConversationDsResponseModel> conversations = ConversationListDsGateway.getActiveConversationIds(userId);

        // Convert the List of ConversationDsResponseModel to an ArrayList of ConversationResponseModel
        ArrayList<ConversationResponseModel> conversationList = new ArrayList<>();
        for (ConversationDsResponseModel conversation : conversations) {
            // Convert the ConversationDsResponseModel to ConversationResponseModel
            ConversationResponseModel conversationResponse = convertToConversationResponse(conversation);
            conversationList.add(conversationResponse);
        }

        return conversationList;
    }

    private ConversationResponseModel convertToConversationResponse(ConversationDsResponseModel conversationDs) {
        String partnerName = ConversationResponseModel.getConversationPartnerName();
//        String lastMessage = ConversationResponseModel.getLastMessage();
//        String lastMessageTime = ConversationResponseModel.getLastMessageTime();
        String conversationId = ConversationResponseModel.getConversationId();

        return new ConversationResponseModel(partnerName, conversationId);
    }

    @Override
    public ArrayList<ConversationResponseModel> getActiveConversations(String userId) {
        // Fetch active conversations from the data source gateway
        ArrayList<ConversationDsResponseModel> conversations = (ArrayList<ConversationDsResponseModel>)
                ConversationListDsGateway.getActiveConversationIds(userId);

        // Convert the List to an ArrayList if needed
        ArrayList<ConversationResponseModel> conversationList = new ArrayList<>(conversations);

        return conversationList;
    }

}

