<!DOCTYPE html>
<html>

<head>
	<meta content='text/html; charset=UTF-8' http-equiv='Content-Type' />
</head>

<body style="background-color: #C9ECFF;display: flex;-ms-flex-pack: center !important;justify-content: center; color:black">
	<div style="  position: relative;width: 100%;margin-left:30%;flex: 0 0 41.666667%;max-width: 41.666667%;background-color: #fff !important">
		<img style="display: block !important;width: 100% !important;" src="https://i.imgur.com/fQMYzi2.png" alt="">
		<div style="padding: 1rem !important;display: block !important;">
			<h3 style="margin-top:0;">
				Dear, ${userFullName}.
			</h3>
			<div>
				 Thank you for your interest in our furniture store and for submitting your design request. We are excited to work with you to bring your vision to life!
            </div>
            <p>
                 We have received your design form and would like to confirm that we have received it. Our team of skilled craftsmen and designers will review your request and provide a quote for the project within the next ${numDay} business days.
            </p>
            <p>
                 Once we receive your approval on the quote, we will begin working on your custom furniture piece(s). We will keep you informed throughout the process and ensure that your satisfaction is our top priority.
            </p>
			<p>This is your design:</p>
			<div style="border: 2px solid #3ea2dc;border-radius: 5px;">
                <img src=${designRequest.imgUrl} alt="" style="display: block !important;width: 100% !important;border-radius: 5px;">
                <p style="padding:0px 5px"> Description: ${designRequest.description}
			</div>
			<p>
                 Please do not hesitate to contact us if you have any questions or concerns regarding your project. We appreciate your business and look forward to working with you.
            </p>
			<p>
			    Best regards, <br/>
                <em>BK Furniture<em>
            </p>
		</div>
		<div style="background-color: #0795DF">
			<img src="https://i.imgur.com/VGtipqs.png" alt="" style="display: block !important;width: 100% !important;">
		</div>
	</div>

</body>

</html>