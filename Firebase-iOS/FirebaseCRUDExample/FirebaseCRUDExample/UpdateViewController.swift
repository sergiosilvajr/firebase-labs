//
//  UpdateViewController.swift
//  FirebaseCRUDExample
//
//  Created by Luis Sergio da Silva Junior on 06/02/17.
//  Copyright © 2017 Luis Sergio. All rights reserved.
//

import UIKit
import Firebase
import FirebaseDatabase

class UpdateViewController: UIViewController {
    public var task: Task?
    var ref:FIRDatabaseReference!
    var userID: String?
    
    @IBOutlet weak var updateText: UITextField!
    override func viewDidLoad() {
        super.viewDidLoad()

        ref = FIRDatabase.database().reference()
        updateText.text = task?.desc
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier=="update"{
            if let currentTask = task {
               if !((updateText.text?.isEmpty)!) {
                    self.ref.child("tasks").child(self.userID!).child(currentTask.id).updateChildValues(["desc": updateText.text])
                }
            }
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
