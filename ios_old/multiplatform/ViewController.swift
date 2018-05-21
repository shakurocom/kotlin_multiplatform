import UIKit
import KMulti

class ViewController: UIViewController, KMultiAsyncCallView {
    
    var presenter: KMultiAsyncCallPresenter!
    
    override func awakeFromNib() {
        super.awakeFromNib()
      self.presenter = KMultiAsyncCallPresenter(api: KMultiAsyncApi())
    }
        
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        presenter.attach(view: self)
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        presenter.detach()
    }
}

