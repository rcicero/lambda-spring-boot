/*
 * Copyright 2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package com.amazonaws.serverless.sample.springboot.controller;



import com.amazonaws.serverless.sample.springboot.exception.TesteException;
import com.amazonaws.serverless.sample.springboot.model.Pet;
import com.amazonaws.serverless.sample.springboot.model.PetData;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@EnableWebMvc
public class PetsController {

    @RequestMapping(path = "/pets", method = RequestMethod.GET)
    public ResponseEntity<Object> listPets(@RequestParam("limit") Optional<Integer> limit, Principal principal) {

        int randomNum = 0 + (int)(Math.random() * 100);

        if(randomNum > 50){
            return new ResponseEntity<Object>("Maior", HttpStatus.BAD_REQUEST);
        }else{
            int queryLimit = 10;
            if (limit.isPresent()) {
                queryLimit = limit.get();
            }

            Pet[] outputPets = new Pet[queryLimit];

            for (int i = 0; i < queryLimit; i++) {
                Pet newPet = new Pet();
                newPet.setId(UUID.randomUUID().toString());
                newPet.setName(PetData.getRandomName());
                newPet.setBreed(PetData.getRandomBreed());
                newPet.setDateOfBirth(PetData.getRandomDoB());
                outputPets[i] = newPet;
            }

            return new ResponseEntity<Object>(outputPets, HttpStatus.OK);
        }



    }

}
