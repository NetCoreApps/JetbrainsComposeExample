using System.Collections.Generic;
using ServiceStack;

namespace JetbrainsComposeDesktopExample.ServiceModel;

[Route("/hello")]
[Route("/hello/{Name}")]
public class Hello : IReturn<HelloResponse>
{
    public string Name { get; set; }
}

public class HelloResponse
{
    public string Result { get; set; }
}

public class SearchFiles : IReturn<SearchFilesResponse>
{
    public string Pattern { get; set; }
}

public class SearchFilesResponse
{
    public List<string> Results { get; set; }
}
