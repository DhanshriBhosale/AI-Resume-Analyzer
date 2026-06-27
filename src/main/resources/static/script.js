function uploadResume() {

    const fileInput =
        document.getElementById("resumeFile");

    const resultBox =
        document.getElementById("result");

    if (fileInput.files.length === 0) {
        resultBox.innerText =
            "Please select a PDF.";
        return;
    }

    const formData = new FormData();

    formData.append(
        "file",
        fileInput.files[0]
    );

    resultBox.innerText =
        "Analyzing...";

    fetch("/upload", {
        method: "POST",
        body: formData
    })
    .then(response => response.text())
    .then(data => {
        resultBox.innerText = data;
    })
    .catch(error => {
        resultBox.innerText =
            "Error uploading resume.";
    });
}