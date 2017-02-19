
var button = document.getElementById('login-button')

button.addEventListener('click', function() {

  const url = '/api/user/login';


  const data = prepareRequest();

  const request = {
    method: 'POST',
    body: data
  }


  console.log('sending request...')
  fetch(url, request)
    .then(
      handleLoginResponse,
      function(error) {
        console.log('buu!');
      });
});

function handleLoginResponse(data) {
  if(data.ok) {
    console.log('yey');
  } else {
    data.text().then(function(text) {
      console.log(text);
    });
  }
}

function prepareRequest() {
  const username = document.querySelector('input[name="username"]').value;
  const password = document.querySelector('input[name="password"]').value;

  const data = new FormData();

  data.append('username', username);
  data.append('password', password);
  return data;
}
