/* Options:
Date: 2024-03-01 15:11:58
Version: 8.12
Tip: To override a DTO option, remove "//" prefix before updating
BaseUrl: https://localhost:5001

//AddServiceStackTypes: True
//AddDocAnnotations: True
//AddDescriptionAsComments: True
//IncludeTypes: 
//ExcludeTypes: 
//DefaultImports: 
*/

"use strict";
export class HelloResponse {
    /** @param {{result?:string}} [init] */
    constructor(init) { Object.assign(this, init) }
    /** @type {string} */
    result;
}
export class SearchFilesResponse {
    /** @param {{results?:string[]}} [init] */
    constructor(init) { Object.assign(this, init) }
    /** @type {string[]} */
    results;
}
export class Hello {
    /** @param {{name?:string}} [init] */
    constructor(init) { Object.assign(this, init) }
    /** @type {string} */
    name;
    getTypeName() { return 'Hello' }
    getMethod() { return 'GET' }
    createResponse() { return new HelloResponse() }
}
export class SearchFiles {
    /** @param {{pattern?:string}} [init] */
    constructor(init) { Object.assign(this, init) }
    /** @type {string} */
    pattern;
    getTypeName() { return 'SearchFiles' }
    getMethod() { return 'GET' }
    createResponse() { return new SearchFilesResponse() }
}

