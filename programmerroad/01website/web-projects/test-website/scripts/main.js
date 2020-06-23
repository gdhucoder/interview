
function multiply(num1, num2) {
    let result = num1 * num2;
    return result;
  }

let myImage = document.querySelector('img');

myImage.onclick = function(){
    let mySrc = myImage.getAttribute('src')
    if (mySrc === 'images/cat-01.png') {
        myImage.setAttribute('src', 'images/pic-01.jpg');
    } else {
        myImage.setAttribute('src', 'images/cat-01.png');
    }
}

let myButton = document.querySelector('button');
let myHeading = document.querySelector('h1');

function setUserName() {
    let myName = prompt('请输入你的名字。');
    if (!myName || myName === null) {
        setUserName();
    } else {
        localStorage.setItem('name', myName);
        myHeading.textContent = '猫爷酷毙了, ' + myName;
    }
}

myButton.onclick = function(){
    setUserName();
}

if(!localStorage.getItem('name')) {
    setUserName();
} else {
    let storedName = localStorage.getItem('name');
    myHeading.textContent = '猫爷酷毙了, ' + storedName;
}