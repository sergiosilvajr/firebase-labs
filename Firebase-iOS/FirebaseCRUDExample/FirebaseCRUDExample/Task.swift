//
//  Task.swift
//  FirebaseCRUDExample
//
//  Created by Luis Sergio da Silva Junior on 06/02/17.
//  Copyright © 2017 Luis Sergio. All rights reserved.
//

import Foundation

public class Task{
    var id: String!
    var desc: String!
    
    public init(id: String, desc: String){
        self.id = id
        self.desc = desc
    }
}
