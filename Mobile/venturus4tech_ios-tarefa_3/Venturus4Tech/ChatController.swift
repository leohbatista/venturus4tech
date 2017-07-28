//
//  ChatController.swift
//  Venturus4Tech
//
//  Created by Gustavo Reder Cazangi on 21/07/17.
//  Copyright © 2017 Venturus. All rights reserved.
//

import UIKit

class ChatController : UIViewController, UITableViewDataSource {
    
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var userMessage: UITextField!
    
    var userNick : String?;
    
    var msgs : [[String:Any]] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.dataSource = self
    }
    
    @IBAction func onSendClick(_ sender: Any) {
        var json : [String:Any] = [:]
        json["author"] = userNick
        json["message"] = userMessage.text
        json["sent"] = "12:34"
        msgs.append(json)
        
        tableView.beginUpdates()
        tableView.insertRows(at: [IndexPath(row: msgs.count-1,section:0)], with: <#T##UITableViewRowAnimation#>)
        tableView.endUpdates()
        
        //self.dismiss(animated: false, completion: nil) = voltar
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return msgs.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "celula_chat", for: indexPath) as! ChatCell
        let author : String = msgs[indexPath.row]["author"] as! String
        
        cell.dateLabel.text = msgs[indexPath.row]["sent"] as! String
        cell.nameLabel.text = author
        cell.initialLabel.text = "\(author.characters.first!)"
        cell.messageLabel.text = msgs[indexPath.row]["message"] as! String
        return cell
    }
    
    
}
