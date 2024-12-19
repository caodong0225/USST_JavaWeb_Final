document.addEventListener('DOMContentLoaded', () => {
    const createAdForm = document.getElementById('createAdForm');
    const adList = document.getElementById('adList');
    const adDetails = document.getElementById('adDetails');

    fetchAllAdvertisements();

    createAdForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const adTitle = e.target.adTitle.value;
        const adImages = e.target.adImage.files;
        const adFeature = e.target.adFeature.value;

        const formData = new FormData();
        formData.append('adName', adTitle);
        formData.append('adFeature', adFeature);

        // 遍历并添加所有图片文件
        for (let i = 0; i < adImages.length; i++) {
            formData.append('adImages', adImages[i]);
        }

        createAdvertisement(formData);
        e.target.reset();
    });

    function createAdvertisement(advertisement) {
        fetch('/advertisements/create', {
            method: 'POST',
            body: advertisement
        })
            .then(() => fetchAllAdvertisements())
            .catch(error => console.error('Error creating advertisement:', error));
    }

    function fetchAllAdvertisements() {
        fetch('/advertisements/all')
            .then(response => response.json())
            .then(data => {
                adList.innerHTML = '';
                data.forEach(ad => {
                    const li = document.createElement('li');
                    li.textContent = ad.adName || 'Untitled';
                    li.addEventListener('click', () => fetchAdvertisementDetails(ad.adId));
                    adList.appendChild(li);
                });
            })
            .catch(error => console.error('Error fetching advertisements:', error));
    }

    function fetchAdvertisementDetails(adId) {
        fetch(`/advertisements/${adId}`)
            .then(response => response.json())
            .then(data => {
                // 解析图片URL的JSON字符串
                const imageUrls = JSON.parse(data.adImageUrl);

                adDetails.innerHTML = `
                <p><strong>Title:</strong> ${data.adName || 'Untitled'}</p>
                <p><strong>Feature:</strong> ${data.adFeature || 'No feature provided'}</p>
                <p><strong>Images:</strong></p>
            `;

                // 遍历所有图片URL，并为每个URL创建一个img元素
                imageUrls.forEach(imageUrl => {
                    const img = document.createElement('img');
                    img.src = imageUrl;
                    img.alt = 'Advertisement Image';
                    adDetails.appendChild(img);
                });

                // 添加跳转到广告详细页面的链接
                const viewDetailsLink = document.createElement('a');
                viewDetailsLink.href = `/ad/${adId}`;
                viewDetailsLink.textContent = 'View Details';
                adDetails.appendChild(viewDetailsLink);
            })
            .catch(error => console.error('Error fetching advertisement details:', error));
    }
});