import axios from 'axios'

export function getAds (callback, username) {
    console.log('getAds')
    // console.log('login')
    axios.get('/venue/ad',
        {
            headers: {
                'Content-Type': 'application/json'
            }
        }
    )
        .then(function (response) {
            // console.log(response.data)
            callback(response.data)
        })
        .catch(function (error) {
        })
}
