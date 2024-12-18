document.addEventListener('DOMContentLoaded', () => {
    const createAdForm = document.getElementById('createAdForm');
    const adList = document.getElementById('adList');
    const adDetails = document.getElementById('adDetails');

    fetchAllAdvertisements();

    createAdForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const adTitle = e.target.adTitle.value;
        const adImage = e.target.adImage.files[0];
        const adFeature = e.target.adFeature.value;

        const formData = new FormData();
        formData.append('adName', adTitle);
        formData.append('adImage', adImage);
        formData.append('adFeature', adFeature);

        createAdvertisement(formData);
        e.target.reset();
    });

    function createAdvertisement(advertisement) {
        fetch('/advertisements/create', {
            method: 'POST',
            body: advertisement
        })
            .then(() => fetchAllAdvertisements());
    }

    function fetchAllAdvertisements() {
        fetch('/advertisements/all')
            .then(response => response.json())
            .then(data => {
                adList.innerHTML = '';
                data.forEach(ad => {
                    const li = document.createElement('li');
                    li.textContent = ad.adName || 'Untitled'; // 使用 ad.adName 或者提供默认值
                    li.addEventListener('click', () => fetchAdvertisementDetails(ad.adId)); // 确认这里使用的是 adId
                    adList.appendChild(li);
                });
            })
            .catch(error => console.error('Error fetching advertisements:', error));
    }


    function fetchAdvertisementDetails(adId) {
        fetch(`/advertisements/${adId}`)
            .then(response => response.json())
            .then(data => {
                adDetails.innerHTML = `
                <p><strong>Title:</strong> ${data.adName || 'Untitled'}</p>
                <p><strong>Feature:</strong> ${data.adFeature || 'No feature provided'}</p>
                <p><strong>Image:</strong></p>
                <img src="${data.adImageUrl || 'no-image.jpg'}" alt="Advertisement Image">
            `;
            })
            .catch(error => console.error('Error fetching advertisement details:', error));
    }

});