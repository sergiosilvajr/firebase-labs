//
//  TaskViewController.swift
//  FirebaseCRUDExample
//
//  Created by Luis Sergio da Silva Junior on 06/02/17.
//  Copyright © 2017 Luis Sergio. All rights reserved.
//

import UIKit
import Firebase
import FirebaseDatabase

class TaskViewController: UIViewController, OnCreateTaskProtocol {
    var ref:FIRDatabaseReference!
    var userID: String = ""
    
    @IBOutlet weak var nameTask: UITextField!
    override func viewDidLoad() {
        super.viewDidLoad()

        ref = FIRDatabase.database().reference()
        userID = (FIRAuth.auth()?.currentUser?.uid)!
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    func createTask(){
        if !nameTask.text!.isEmpty{
           // self.ref.child("tasks").child(userID).setValue(["desc" : nameTask.text!])
            self.ref.child("tasks").child(userID).childByAutoId().updateChildValues(["desc" : nameTask.text!])
        }
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "createtask"{
            createTask()
        }
    }
  
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
