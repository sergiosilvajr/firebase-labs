//
//  OnCreateUserListener.swift
//  FirebaseCRUDExample
//
//  Created by Luis Sergio da Silva Junior on 06/02/17.
//  Copyright © 2017 Luis Sergio. All rights reserved.
//

import Foundation

public protocol OnCreateUserProtocol{
   func getNewUser() -> User
   func createNewUser()
}
