import Foundation
import EasyDi
import KMulti

class ServiceAssembly: Assembly {
  
  var apiBaseUrl: URL! = URL(string: "https://api.github.com")
  
  var githubUser = "shakurocom"
  
  var reposParser: GithubReposParser {
    return define(init: GithubReposParser())
  }

  var branchesParser: GithubBranchesParser {
    return define(init: GithubBranchesParser())
  }

  var reposRepository: ReposRepository {
    return define(scope: .lazySingleton, init: ReposRepository(
      baseUrl: self.apiBaseUrl,
      githubUser: self.githubUser,
      reposParser: self.reposParser,
      branchesParser: self.branchesParser
    ))
  }
  
  var reposInteractor: KMultiReposInteractor {
      return define(init: KMultiReposInteractor(
          repository: self.reposRepository,
          context: KMultiAsyncDispatcher()
      ))
  }
}
