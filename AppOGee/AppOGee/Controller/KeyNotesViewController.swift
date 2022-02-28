//
//  KeyNotesViewController.swift
//  AppOGee
//
//  Created by Tejaswini N on 28/02/22.
//  Copyright © 2022 Tejaswini N. All rights reserved.
//

import UIKit
import Firebase

class KeyNotesViewController: UIViewController  ,UITableViewDataSource, UITableViewDelegate{
  
    @IBOutlet weak var Keynotestableview: UITableView!

    var refKeynotes = DatabaseReference()
    var keyNotesList = NSMutableArray()
  

    override func viewDidLoad() {
        super.viewDidLoad()
        Keynotestableview.delegate = self
        Keynotestableview.dataSource = self
        Keynotestableview.backgroundView = UIImageView(image: UIImage(named: "features_bg.png"))

        refKeynotes = Database.database().reference().child("features/keynotes");
         self.keyNotesList.removeAllObjects()

        refKeynotes.observe(DataEventType.value, with: {(snapshot) in
            self.keyNotesList = snapshot.value as! NSMutableArray
            print(self.keyNotesList)
            self.Keynotestableview.reloadData()
        })
    }

    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        self.keyNotesList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "Keynotescell",
                                                     for: indexPath as IndexPath) as! KeyNotesTableViewCell
        let dict =  keyNotesList[indexPath.row] as! NSDictionary
        let imageURL = URL(string: dict["image"] as! String )
        cell.bgImg.sd_setImage(with:imageURL)
        cell.lbTitle.text = dict["title"] as! String
        cell.lbDescription.attributedText = (dict["description"] as! String).html2AttributedString
        cell.lbDescription.textColor = UIColor.white
        cell.lbDescription.font = cell.lbDescription.font.withSize(16)

        return cell
    }
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 450
    }
}
extension Data {
    var html2AttributedString: NSAttributedString? {
        do {
            return try NSAttributedString(data: self, options: [.documentType: NSAttributedString.DocumentType.html, .characterEncoding: String.Encoding.utf8.rawValue], documentAttributes: nil)
        } catch {
            print("error:", error)
            return  nil
        }
    }
    var html2String: String { html2AttributedString?.string ?? "" }
}
extension StringProtocol {
    var html2AttributedString: NSAttributedString? {
        Data(utf8).html2AttributedString
    }
    var html2String: String {
        html2AttributedString?.string ?? ""
    }
}
