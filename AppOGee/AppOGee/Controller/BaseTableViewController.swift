//
//  BaseTableViewController.swift
//  AppOGee
//
//  Created by Tejaswini N on 05/03/22.
//  Copyright © 2022 Tejaswini N. All rights reserved.
//

import UIKit
import Firebase
import PUGifLoading

class BaseTableViewController: UITableViewController {
    let appstableView =  EWArcTableView(frame: CGRect(x: 0, y: 0, width: UIScreen.main.bounds.size.width - 90 , height: UIScreen.main.bounds.size.height), style: .plain)
    
    var refProjects = DatabaseReference()
    fileprivate var items = NSMutableArray()
    let loading = PUGIFLoading()
    private let btnShowProjects: UIButton = {
        let applogo = UIButton(frame: CGRect(x: 0, y: UIScreen.main.bounds.size.height / 2, width: 50, height: 50))
        applogo.setTitle("", for: .normal)
        applogo.setImage(UIImage(named: "arrow_left.png"), for: .normal)
        return applogo
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if let window = UIApplication.shared.keyWindow {
             window.addSubview(appstableView)
             window.addSubview(btnShowProjects)
        }
        
        drawMyView()
        btnShowProjects.addTarget(self, action: #selector(btnClick), for: .touchUpInside)

        appstableView.isHidden = true
        refProjects = Database.database().reference().child("/myApps");
        self.items.removeAllObjects()
       
        refProjects.observe(DataEventType.value, with: {(snapshot) in
               self.items = snapshot.value as! NSMutableArray
               print(self.items)
               self.appstableView.reloadData()
               self.loading.hide()
        })
        
        if(appstableView.isHidden) {
            self.btnShowProjects.frame.origin.x = -15

        } else {
            self.btnShowProjects.frame.origin.x = 260

        }
    }
    @objc func btnClick() {
        UIView.animate(withDuration:0.1, animations: { () -> Void in
            if self.btnShowProjects.transform == .identity {
                self.btnShowProjects.transform = CGAffineTransform(rotationAngle: CGFloat(M_PI * 0.999))
                    } else {
                        self.btnShowProjects.transform = .identity
                    }
            }, completion: {(_ finished: Bool) -> Void in
                if(self.appstableView.isHidden) {
                    self.appstableView.isHidden = false
                    self.btnShowProjects.frame.origin.x = 260
                } else{
                    self.appstableView.isHidden = true
                    self.btnShowProjects.frame.origin.x = -5
                }
        })
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        if let window = UIApplication.shared.keyWindow {
                window.addSubview(appstableView)
             window.addSubview(btnShowProjects)
        }
        drawMyView()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        appstableView.removeFromSuperview()
        
    }
    
    private func drawMyView() {

        appstableView.separatorStyle = .none
        appstableView.isOpaque = false
        /// 修改cell高度要同时修改rowHeight
        appstableView.rowHeight = 120
        appstableView.dataSource = self
        appstableView.delegate = self
        /// reloadData后contentOffset更改,导致布局效果问题 添加三行
        appstableView.estimatedRowHeight = 0
        appstableView.estimatedSectionHeaderHeight = 0
        appstableView.estimatedSectionFooterHeight = 0
        appstableView.showsHorizontalScrollIndicator = false
        appstableView.showsVerticalScrollIndicator = false
        appstableView.register(EWArcTableViewCell.self, forCellReuseIdentifier: EWArcTableViewCell.identifier)
        appstableView.backgroundView = UIImageView(image: UIImage(named: "semi_circle"))
        appstableView.backgroundColor = .clear

    }
    override func numberOfSections(in tableView: UITableView) -> Int {
        return super.numberOfSections(in: tableView) - 1
    }
    override func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if(tableView == appstableView) {
            return 120
        } else{
            return super.tableView(tableView, heightForRowAt: indexPath)
        }
    }
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if(tableView == appstableView) {
            if(self.items.count > 1) {
                return self.items.count
            } else {
                return 0
            }
        }else {
            return super.tableView(tableView, numberOfRowsInSection: section)
        }
    }
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if(tableView == appstableView) {
            guard let cell = tableView.dequeueReusableCell(withIdentifier:  EWArcTableViewCell.identifier) as? EWArcTableViewCell else {
                return EWArcTableViewCell()
            }
            let dict =  items[indexPath.row] as! NSDictionary
            cell.setPersonModel(logo: dict["logo"] as! String)
            return cell
        } else {
            return super.tableView(tableView, cellForRowAt: indexPath)

        }
    }
}


