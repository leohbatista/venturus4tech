//
//  ViewController.swift
//  ventuchat
//
//  Created by vntlab on 7/27/17.
//  Copyright © 2017 vntlab. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var label: UILabel!
    
    var clicks = 0
    let maximo = 40
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func onButtonClick(_ sender: Any) {
        let vc = self.storyboard!.instantiateViewController(withIdentifier: "chat")
        self.present(vc,animated: true, completion: nil)
    }

}

