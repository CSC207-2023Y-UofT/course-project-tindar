package com.courseproject.tindar.usecases.conversations;

import com.courseproject.tindar.usecases.likelist.LikeListDsResponseModel;
import com.courseproject.tindar.usecases.likelist.LikeListResponseModel;

import java.util.ArrayList;
import java.util.List;
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
        private ConversationListDsGateway dsGateway;

    public ConversationListInteractor(ConversationListDsGateway dsGateway) {
        this.dsGateway = dsGateway;
    }
    //TODO : do NOT review yet since it may change with database helper function

    public ArrayList<ConversationResponseModel> getActiveConversations(String userId) {
        ArrayList<ConversationDsResponseModel> conversations =
                ConversationListDsGateway.getActiveConversationIds(userId);
        ArrayList<String> partnerUserIds = ArrayList<>();
        ArrayList<String> conversationIds = ArrayList<>();

        for (Object conversation : conversations) {
            if (userId == conversation.getUserId1()) {
                partnerUserIds.add(conversation.getUserId2())
            } else {
                partnerUserIds.add(conversation.getUserId1())
            }
            conversationIds.add(conversation.getConverstionId())
        }


        // getDisplayNames is another db function
        ArrayList<String> partnerNames = conversationListDsGateway.getDisplayNames(partnerUserIds);


        ArrayList<ConversationMessageResponseModel> lastMessages=
                conversationListDsGateway.getLastMessages(conversationIds);

        ArrayList<ConversationResponseModel> conversationsToReturn = ArrayList<>();

        for (int i = 0; i < conversationIds.length; i++) {
            conversationsToReturn.add(new ConversationResponseModel(partnerNames.get(i),
                    lastMessages.get(i).getLastMessage(),
                    lastMessages.get(i).getLastMessageTime(), conversationIds.get(i)), )

        }

        return conversationsToReturn
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public ArrayList<ConversationResponseModel> addOrUpdateAllConversations(String userId) {
        return null;
    }
}
//------------------------------------------------------------------------------------
//public class ConversationMessageResponseModel {
//    @Getter private String lastMessage;
//    @Getter private String lastMessageTime;
//
//    public ConversationMessageResponseModel(String lastMessage, String lastMessage) {
//        this.lastMessage = lastMessage;
//        this.lastMessageTime = lastMessageTime;
//    }
//}

//public class ConversationListInteractor implements ConversationListInputBoundary {
//
//    private ConversationListDsGateway dsGateway;
//
//    public ConversationListInteractor(ConversationListDsGateway dsGateway) {
//        this.dsGateway = dsGateway;
//    }
//
//    /**
//     * Retrieves a list of active conversations for the given user ID.
//     *
//     * @param userId The ID of the user whose active conversations are to be retrieved.
//     * @return List of active conversations for the user.
//     */
//
//
//
//    /**
//     * @param userId
//     * @return
//     */
//    @Override
//    public ArrayList<ConversationResponseModel> addOrUpdateAllConversations(String userId) {
//        return null;
//    }
//
//    @Override
//    public ConversationResponseModel getConvoPartnerNames(String userId) {
//        // This method returns the display names if the users in the match list for front end purposes
//        ArrayList<String[]> arrayListConvoPartner = ConversationListDsGateway.readMatchList(userId);
//        ArrayList<String> matches = new ArrayList<>();
//        for (String[] arrayListMatch : arrayListMatches) {
//            if (Objects.equals(arrayListMatch[0], userId)) {
//                matches.add(arrayListMatch[1]);
//            } else {
//                matches.add(arrayListMatch[0]);
//            }
//        }
//
//        ArrayList<LikeListDsResponseModel> matchedUsers = likeListDsGateway.readDisplayNames(matches);
//
//        int numberOfMatches = matchedUsers.size();
//        String[] displayNames = new String[numberOfMatches];
//        String[] userIds = new String[numberOfMatches];
//        for (int i = 0; i < numberOfMatches; i++) {
//            displayNames[i] = matchedUsers.get(i).getDisplayName();
//            userIds[i] = matchedUsers.get(i).getUserId();
//        }
//
//        return new LikeListResponseModel(userIds, displayNames);
//    }
//}
//
