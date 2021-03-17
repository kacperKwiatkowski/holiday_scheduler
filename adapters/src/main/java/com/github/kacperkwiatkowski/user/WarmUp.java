//package com.github.kacperkwiatkowski.user;
//
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Service;
//
//@Service
//public class WarmUp implements ApplicationListener<ContextRefreshedEvent> {
//    private final UserInitializer userInitializer;
//
//    WarmUp(UserInitializer userInitializer) {
//        this.userInitializer = userInitializer;
//    }
//
//    @Override
//    public void onApplicationEvent(final ContextRefreshedEvent event) {
//        userInitializer.init();
//    }
//}
