//
//  SignUpViewController.swift
//  FirebaseCRUDExample
//
//  Created by Luis Sergio da Silva Junior on 06/02/17.
//  Copyright © 2017 Luis Sergio. All rights reserved.
//

import UIKit
import FirebaseAuth

class SignUpViewController: UIViewController, OnCreateUserProtocol{
    var newUser: User = User()
    
    @IBOutlet weak var loginTxt: UITextField!
    @IBOutlet weak var passwordTxt: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    func getNewUser() -> User{
        return newUser
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "signup"{
            createNewUser()
        }
    }
    
    func createNewUser() {
        if !loginTxt.text!.isEmpty{
            newUser.login = loginTxt.text
        }
        if !passwordTxt.text!.isEmpty{
            newUser.password = passwordTxt.text
        }

        FIRAuth.auth()?.createUser(withEmail: newUser.login, password: newUser.password, completion: {
            (user,error) in
            if (error != nil){
                //you should does something here
            }
        })
    }
}
