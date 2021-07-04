const express = require("express");
const path = require("path");

const app = express();

app.use(express.static(__dirname + "/dist/senchauffeur"));

app.listen(process.env.PORT || 4200);

app.get("/*", function(req, res) {
    res.sendFile(path.join(__dirname + "/dist/senchauffeur/index.html"));
});

console.log("App is listenning!");