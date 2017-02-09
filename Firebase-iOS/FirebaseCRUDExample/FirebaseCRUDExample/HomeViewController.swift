//
//  HomeViewController.swift
//  FirebaseCRUDExample
//
//  Created by Luis Sergio da Silva Junior on 06/02/17.
//  Copyright © 2017 Luis Sergio. All rights reserved.
//

import UIKit
import FirebaseAuth
import FirebaseDatabase

class HomeViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {

    @IBOutlet weak var tableView: UITableView!
    let userID = FIRAuth.auth()?.currentUser?.uid
     var ref:FIRDatabaseReference!
    
    var tasksDesc = [Task]()
    var selectedIndex = -1
    
    override func viewDidLoad() {
        super.viewDidLoad()
        ref = FIRDatabase.database().reference()
        self.tableView.allowsMultipleSelectionDuringEditing = true
        
        ref.child("tasks").child(userID!).observe(.value, with: { (snapshot) in
            // Get user value
            if snapshot.exists(){
                self.tasksDesc = [Task]()
                let task = snapshot.value as? NSDictionary
                
                for (key, value) in task!{
                    self.convertToJson(dic: value as! NSDictionary,key: key as! String)
                }
                self.tableView.reloadData()
                // ...

            }
        })
        // Do any additional setup after loading the view.
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        selectedIndex = indexPath.row
        performSegue(withIdentifier: "show", sender: self)
    }
    
    func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        return true
    }
    
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete{
            let taskId = self.tasksDesc[indexPath.row].id
            ref.child("tasks").observe(.value, with: { (snapshot) -> Void in
                if snapshot.exists(){
                    self.ref.child("tasks").child(self.userID!).child(taskId!).removeValue()
                }
             
            })
        
        }
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return tasksDesc.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "taskcell", for: indexPath)
        cell.textLabel!.text = tasksDesc[indexPath.row].desc
        
        return cell
    }

    func tableView(_ tableView: UITableView, titleForDeleteConfirmationButtonForRowAt indexPath: IndexPath) -> String? {
        return "Delete"
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "logout"{
            do{
                try FIRAuth.auth()!.signOut()
                print("logout done")
            }catch{
                print("logout error")
            }
        }
        if segue.identifier == "show"{
                let upController = segue.destination as! UpdateViewController
                upController.task = tasksDesc[selectedIndex]
                upController.userID = userID
            
            print("show task")
        }
    }

    @IBAction func unwindToHomeLoggedUser(segue: UIStoryboardSegue)
    {
        if segue.identifier == "createtask"{
            
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

    private func convertToJson(dic: NSDictionary, key: String){
        do {
            let jsonData = try JSONSerialization.data(withJSONObject: dic, options: .prettyPrinted)
            let decoded = try JSONSerialization.jsonObject(with: jsonData, options: [])
            if let dictFromJSON = decoded as? [String:String] {
                tasksDesc.append(Task(id: key, desc: (dictFromJSON["desc"])!))
            }
        } catch {
            print(error.localizedDescription)
        }
    }

}
