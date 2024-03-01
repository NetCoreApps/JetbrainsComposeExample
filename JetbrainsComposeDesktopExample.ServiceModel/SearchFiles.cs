using System.Collections.Generic;
using ServiceStack;

namespace JetbrainsComposeDesktopExample.ServiceModel;

public class SearchFiles : IGet, IReturn<SearchFilesResponse>
{
    public string Pattern { get; set; }
}

public class SearchFilesResponse
{
    public List<string> Results { get; set; }
}
