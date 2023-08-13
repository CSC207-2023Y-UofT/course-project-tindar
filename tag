[1mdiff --git a/app/src/main/java/com/courseproject/tindar/usecases/chat/ChatInteractor.java b/app/src/main/java/com/courseproject/tindar/usecases/chat/ChatInteractor.java[m
[1mindex 2e8d08c..f9cbb52 100644[m
[1m--- a/app/src/main/java/com/courseproject/tindar/usecases/chat/ChatInteractor.java[m
[1m+++ b/app/src/main/java/com/courseproject/tindar/usecases/chat/ChatInteractor.java[m
[36m@@ -11,6 +11,7 @@[m [mpublic class ChatInteractor implements ChatPresenter, ChatActivityController{[m
     private String conversationPartnerUserId;[m
     private String[] userIds;[m
     private ArrayList<MessageModel> messageList;[m
[32m+[m[32m    private ArrayList<ChatPresenter> observers;[m
 [m
     public ChatInteractor(ChatDsGateway chatDsHelper, String userId,[m
                           String conversationPartnerUserId){[m
[36m@@ -44,7 +45,9 @@[m [mpublic class ChatInteractor implements ChatPresenter, ChatActivityController{[m
      */[m
     @Override[m
     public void addChatObserver(ChatPresenter newObserver) {[m
[31m-[m
[32m+[m[32m        if (!this.observers.contains(newObserver)) {[m
[32m+[m[32m            this.observers.add(newObserver);[m
[32m+[m[32m        };[m
     }[m
 [m
     /**[m
[36m@@ -54,7 +57,7 @@[m [mpublic class ChatInteractor implements ChatPresenter, ChatActivityController{[m
      */[m
     @Override[m
     public void deleteChatObserver(ChatPresenter observer) {[m
[31m-[m
[32m+[m[32m        this.observers.remove(observer);[m
     }[m
 [m
     /**[m
[36m@@ -77,10 +80,6 @@[m [mpublic class ChatInteractor implements ChatPresenter, ChatActivityController{[m
      */[m
     @Override[m
     public void updateMessageList(MessageModel newMessage) {[m
[31m-[m
[31m-    }[m
[31m-[m
[31m-    private void notifyObservers(){[m
[31m-        return;[m
[32m+[m[32m        // TODO[m
     }[m
 }[m
