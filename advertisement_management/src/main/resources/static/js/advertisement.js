document.addEventListener('DOMContentLoaded', () => {
    const createAdForm = document.getElementById('createAdForm');
    const adList = document.getElementById('adList');
    const adDetails = document.getElementById('adDetails');

    fetchAllAdvertisements();

    createAdForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const adTitle = e.target.adTitle.value;
        const adUrl = e.target.adUrl.value;

        const advertisement = {
            adName: adTitle,
            adUrl: adUrl
        };

        createAdvertisement(advertisement);
        e.target.reset();
    });

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

    function createAdvertisement(advertisement) {
        fetch('/advertisements/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(advertisement)
        })
            .then(() => fetchAllAdvertisements());
    }

    function fetchAdvertisementDetails(adId) {
        fetch(`/advertisements/${adId}`)
            .then(response => response.json())
            .then(data => {
                adDetails.innerHTML = `
                <p><strong>Title:</strong> ${data.adName || 'Untitled'}</p>
                <p><strong>URL:</strong> <a href="${data.adUrl}" target="_blank">${data.adUrl || 'No URL provided'}</a></p>
            `;
            })
            .catch(error => console.error('Error fetching advertisement details:', error));
    }
});