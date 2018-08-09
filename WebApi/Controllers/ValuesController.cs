using System.Collections.Generic;
using Microsoft.AspNetCore.Mvc;

namespace WebApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ValuesController : ControllerBase
    {
        // GET api/values
        [HttpGet]
        public ActionResult<IEnumerable<string>> Get()
        {
            var stopwatch = System.Diagnostics.Stopwatch.StartNew();
            for (var i = 0; i < 1000000000; i++) { }
            stopwatch.Stop();
            return new string[] { $"{stopwatch.ElapsedMilliseconds}" };
        }
    }
}
