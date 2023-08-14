package com.courseproject.tindar.usecases.conversationlist;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Layer: Use cases - connects controller to database gateway
 *.
 * What is it :
 * This Interactor class mainly implements method define in the ConversationListInputBoundary
 * interface - get/update the conversations for a given user.
 *.
 * It calls DB function to manipulate database / method that does data saving.
 *     getting information from db to pass to controller, controller pass it to UI
 *     it is responsible for
 *.
 * Why does it exists:
 * It exist to creating a clear boundary between the use case contract and its implementation
 *
 */

public class ConversationListInteractor implements ConversationListInputBoundary {
    private final ConversationListDsGateway conversationListDsGateway;

    /**
     * Constructor to initialize the ConversationListInteractor with a ConversationListDsGateway.
     *
     * @param conversationListDsGateway The data source gateway for conversation list data.
     */
    public ConversationListInteractor(ConversationListDsGateway conversationListDsGateway) {
        this.conversationListDsGateway = conversationListDsGateway;
    }

    /**
     * Retrieves an ArrayList of active conversations for a given user.
     *
     * @param userId The user ID for which to fetch active conversations.
     * @return An ArrayList of ConversationResponseModel representing active conversations.
     */
    public ArrayList<ConversationResponseModel> getAllActiveConversations(String userId) {
        ArrayList<ConversationDsResponseModel> conversationUserGroupList =
                conversationListDsGateway.readConversationList(userId);

        ArrayList<String> conversationPartnerUserIds = new ArrayList<>();
        ArrayList<ConversationMessageDsResponseModel> conversationLastMessages = new ArrayList<>();

        for (ConversationDsResponseModel conversationUserGroup : conversationUserGroupList) {
            if (Objects.equals(conversationUserGroup.getUserId1(), userId)) {
                conversationPartnerUserIds.add(conversationUserGroup.getUserId2());
            } else {
                conversationPartnerUserIds.add(conversationUserGroup.getUserId1());
            }
            conversationLastMessages.add(conversationListDsGateway.readLastMessage(conversationUserGroup.getConversationId()));
        }

        ArrayList<String> conversationPartnerDisplayNames = conversationListDsGateway.
                readDisplayNames(conversationPartnerUserIds);

        ArrayList<ConversationResponseModel> activeConversations = new ArrayList<>();

        for (int i = 0; i < conversationUserGroupList.size(); i++) {
            if (conversationLastMessages.get(i) != null){
                activeConversations.add(new ConversationResponseModel(
                        conversationPartnerUserIds.get(i),
                    conversationPartnerDisplayNames.get(i),
                    conversationLastMessages.get(i).getLastMessage() ,
                    conversationLastMessages.get(i).getLastMessageTime().toString()
                ));
            }
        }

        return activeConversations;
    }
}
