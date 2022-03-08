//
//  WorkListViewController.swift
//  AppOGee
//
//  Created by Tejaswini N on 31/05/20.
//  Copyright © 2020 Tejaswini N. All rights reserved.
//

import UIKit
import WebKit

class WebDetailsViewController: UITableViewController , WKUIDelegate,WKNavigationDelegate {
    var webView: WKWebView!

    @IBOutlet var tableview: UITableView!
    @IBOutlet weak var imageView: UIImageView!
    var imgurl = ""
    var weburl = ""
    var webHeight = 0.0
    @IBOutlet weak var featureWebView: UIView!
    
   
    override func viewDidLoad() {
        super.viewDidLoad()
        let imageURL = URL(string:imgurl )
        imageView.sd_setImage(with:imageURL)
        let url = URL(string:weburl)!
        webView.load(URLRequest(url: url))
        if #available(iOS 15.0, *) {
            self.tableview.sectionHeaderTopPadding = 0
        }
       
        
    }
    
    @IBAction func back(_ sender: Any) {
        self.navigationController?.popViewController(animated: true)
    }
    override func viewDidAppear(_ animated: Bool) {
        self.navigationController?.isNavigationBarHidden = true
    }
    func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!) {
        self.webView.evaluateJavaScript("document.readyState", completionHandler: { (complete, error) in
        if complete != nil {
            self.webView.evaluateJavaScript("document.body.scrollHeight", completionHandler: { (height, error) in
                self.webHeight = webView.scrollView.contentSize.height + 250
                self.webView.frame = CGRect(x: 0,y: 0,width: self.featureWebView.frame.width,height:  self.webHeight)
                self.tableview.reloadData()
            })
        }})
          
     }
      override func loadView() {
          super.loadView()
          let myWebView:WKWebView = WKWebView(frame: featureWebView.frame)
          webView = myWebView
          webView.uiDelegate = self
          webView.navigationDelegate = self

          featureWebView.addSubview(webView)
          webView.scrollView.isScrollEnabled = false
      }
      
    override func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return webHeight
        
    }
    
    
//    func zoomOut(imageview : UIView) {
//        UIView.animate(withDuration: 0.5, delay: 0.0, options: UIView.AnimationOptions.curveEaseOut, animations: {
//                // HERE
//            imageview.transform = CGAffineTransform.identity.scaledBy(x: 20, y: 20) // Scale your image
//
//            }) { (finished) in
//
//        }
//    }
  
 }

