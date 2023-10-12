using ServiceStack;
using JetbrainsComposeDesktopExample.ServiceModel;

namespace JetbrainsComposeDesktopExample.ServiceInterface;

public class MyServices : Service
{
    public object Any(Hello request)
    {
        return new HelloResponse { Result = $"Hello, {request.Name}!" };
    }
}

public class SearchFilesServices : Service
{
    public object Any(SearchFiles request)
    {
        var files = VirtualFiles.GetAllMatchingFiles(request.Pattern ?? "*");
        return new SearchFilesResponse {
            Results = files.Map(x => x.VirtualPath)
        };
    }
}