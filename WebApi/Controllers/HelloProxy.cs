using System.Net.Http;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;

namespace WebApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class HelloProxyController : ControllerBase
    {
        // GET api/values
        [HttpGet]
        public async Task<ActionResult<string>> Get()
        {
            var stopwatch = System.Diagnostics.Stopwatch.StartNew();
            var response = await new HttpClient().GetAsync("http://hello-function:8081/hello?name=john");
            var text = await response.Content.ReadAsStringAsync();
            stopwatch.Stop();
            return $"{text} {stopwatch.ElapsedMilliseconds}";
        }
    }
}