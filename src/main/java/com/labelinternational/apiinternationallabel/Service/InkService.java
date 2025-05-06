package com.labelinternational.apiinternationallabel.Service;

import com.labelinternational.apiinternationallabel.Repository.InkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InkService {

    @Autowired
    private InkRepository inkRepository;


}
