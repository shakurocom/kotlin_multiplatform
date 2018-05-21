import Foundation
import EasyDi
import KMulti

class ViewControllerAssembly: Assembly {
    
    lazy var serviceAssembly: ServiceAssembly = self.context.assembly()
    
    func inject(into viewController: ViewController) {
        defineInjection(into: viewController) {
            $0.presenter = KMultiReposPresenter(
                    uiContext: KMultiMainQueueDispatcher(),
                    interactor: self.serviceAssembly.reposInteractor
            )
            return $0
        }
    }
}
