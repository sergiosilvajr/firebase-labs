//
//  ViewController.swift
//  FirebaseCRUDExample
//
//  Created by Luis Sergio da Silva Junior on 06/02/17.
//  Copyright © 2017 Luis Sergio. All rights reserved.
//

import UIKit
import FirebaseAuth

class ViewController: UIViewController {

    @IBOutlet weak var emailLoginText: UITextField!
    @IBOutlet weak var passwordLoginText: UITextField!
    
    @IBAction func onLoginPressed(_ sender: Any) {
        FIRAuth.auth()?.signIn(withEmail: emailLoginText.text!, password: passwordLoginText.text!, completion: {
            (user, error) in
            if error == nil{
                self.performSegue(withIdentifier: "login", sender: self)
            }
            else{
                
            }
        })
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
       
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "login"{
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
    }
    
    @IBAction func unwindToHome(segue: UIStoryboardSegue)
    {
//        if segue.identifier == "signup"{
//            for viewC in (self.navigationController?.childViewControllers)!  where viewC is OnCreateUserProtocol
//            {
//                let controller = viewC as! OnCreateUserProtocol
//                
//            }
//        }
    }
}

