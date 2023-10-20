package com.eventshub.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//public class CloudinaryConfig {
//        @Value("${cloud_name}")
//        private String CLOUD_NAME;
//        @Value("${api_key}")
//        private String API_KEY;
//        @Value("${api_secret}")
//        private String API_SECRET;
//
//        @Bean
//        public Cloudinary cloudinary(){
////        Map<String, String> config = new HashMap<>();
////        config.put("cloud_name", CLOUD_NAME);
////        config.put("api_key", API_KEY);
////        config.put("api_secret", API_SECRET);
////        return new Cloudinary(config);
//            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
//                    "cloud_name", "dhntglxii",
//                    "api_key", "469592551913297",
//                    "api_secret", "HOE2lqzC9u8egOE2NeV1hXYIP4E",
//                    "secure", true));
//            return cloudinary;
//        }
//}
