function basicInfo() {
    var fso = new ActiveXObject("Scripting.FileSystemObject");
    //get file info
    var f1 = fso.GetFile("G:\\node.txt");
    //alert(f1.DateLastModified);

    //get disk info
    var drv;
    var s = '';
    drv = fso.GetDrive(fso.GetDriveName("C:\\"));
    s += drv.VolumeName + '\n';
    s += drv.TotalSize / 1024 + 'kb\n';
    s += drv.FreeSpace / 1024 + 'kb\n';
    //alert(s);

    //get folder info
    var folder = fso.GetFolder("E:\\test");
    //alert(folder);
    //alert(folder.Drive);
    //alert(folder.IsRootFolder);
    //fso.CreateFolder("E:\\test\\aaa");
    //alert(fso.GetBaseName("E:\\test\\aaa"));
    //fso.DeleteFolder("E:\\test\\aaa");
}


//get files in folder
function getFileInFolder(path,surfix) {
    var fso = new ActiveXObject("Scripting.FileSystemObject");
    //var path = "E:\\test";
    var folder = fso.GetFolder(path);
    var fileIterator = new Enumerator(folder.Files);
    var s = "";
    var fileArr = new Array();
    var count = 0;

    while (!fileIterator.atEnd()) {
        count++;
        alert(fileIterator.item().Name);
        var filename = fileIterator.item().Name;
        var curr_surfix = filename.split(".")[1];
        if (curr_surfix == surfix) {
            fileArr.push(filename);
        }
        fileIterator.moveNext();
    }
    //alert(fileArr.length);
    //alert(folder);
    return fileArr;
}