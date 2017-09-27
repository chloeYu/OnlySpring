$(document).ready(function() {
			
	var $messages = $('.messages-content'), d, h, m, i = 0;

	$(window).load(function() {
		connect();
		$messages.mCustomScrollbar();
	});

	// 웹 소켓 변수

	var websock;
	var url = window.location.host;// 웹브라우저의 주소창의 포트까지 가져옴
	var pathname = window.location.pathname;
	var appCtx = pathname.substring(0, pathname.indexOf("/", 2));
	var root = url + appCtx;
					// 문서 시작 호출메소드
					/*
					 * $(document).ready(function() {
					 * $('#message').keypress(function(event) { var keycode =
					 * event.keyCode ? event.keyCode : event.which; if (keycode ==
					 * 13) send(); event.stopPropagation(); });
					 * $('#sendBtn').click(function() { send(); });
					 * $('#enterBtn').click(function() { connect(); });
					 * $('#exitBtn').click(function() { // 퇴장 버튼을 눌렀을 때 호출될 메소드 //
					 * 소켓 닫기 websock.close(); }); });
					 */
					// 메시지를 출력하는 메소드
					function appendMessage(msg) { // msg를 messages-content에 추가
				
							$('<div class="message loading new"><figure class="avatar"><img src="/only/img_all/user.png" /></figure><span></span></div>')
								.appendTo($('.mCSB_container'));
						updateScrollbar();
						setTimeout(
								function() {
									$('.message.loading').remove();
									$('<div class="message new"><figure class="avatar"><img src="/only/img_all/user.png" /></figure>'
													+ msg + '</div>')
											.appendTo($('.mCSB_container'))
											.addClass('new');
									setDate();
									updateScrollbar();
									i++;
								}, 1000 + (Math.random() * 20) * 100);
					}
					
					// 입장 버튼을 눌렀을 때 호출될 메소드
					function connect() {
						websock = new WebSocket("ws://" + root + "/chat-ws");
						websock.onopen = onOpen; // 연결이 될 때 호출될 메소드 설정
						websock.onmessage = onMessage; // 메시지가 왔을 때 호출될 메소드 설정
						websock.onclose = onClose; // 연결이 해제될 때 호출될 메소드 설정
					}
					
					function onOpen(evt) { // 연결이 되었을 때 호출될 메소드
						appendMessage("연결되었습니다.")
					}
					// 메시지가 왔을 때 호출될 메소드
					/*function onMessage(evt) { 
						var data = evt.data;
						appendMessage(data);
					}
					*/
					function onClose(evt) { // 연결이 해제될 때 호출되는 메소드
						appendMessage("연결을 끊었습니다.");
					}
					
					function updateScrollbar() {
						$messages.mCustomScrollbar("update").mCustomScrollbar(
								'scrollTo', 'bottom', {
									scrollInertia : 10,
									timeout : 0
								});
					}

					function setDate() {
						d = new Date()
						if (m != d.getMinutes()) {
							m = d.getMinutes();
							$(
									'<div class="timestamp">' + d.getHours()
											+ ':' + m + '</div>').appendTo(
									$('.message:last'));
							$(
									'<div class="checkmark-sent-delivered">&check;</div>')
									.appendTo($('.message:last'));
							$('<div class="checkmark-read">&check;</div>')
									.appendTo($('.message:last'));
						}
					}

					function insertMessage() {
						var msg = $('.message-input').val();
						var str = $(
								'<div class="message message-personal">' + msg
								+ '</div>').appendTo(
						$('.mCSB_container')).addClass('new');
						if ($.trim(msg) == '') {
							return false;
						}
						websock.send(msg);
						$('.message-input').val("");
						setDate();
						updateScrollbar();

						/*setTimeout(function() {
							receiveMessage();
						}, 1000 + (Math.random() * 20) * 100);*/
					}

					// 전송 버튼을 눌렀을 때 호출되는 메소드
					$('.message-submit').click(function() {
						insertMessage();
					});

					$(window).on('keydown', function(e) {
						if (e.which == 13) {
							insertMessage();
							return false;
						}
					})

					/*var Fake = [ 'Hi there, I\'m Jesse and you?',
							'Nice to meet you', 'How are you?',
							'Not too bad, thanks', 'What do you do?',
							'That\'s awesome',
							'Codepen is a nice place to stay',
							'I think you\'re a nice person',
							'Why do you think that?', 'Can you explain?',
							'Anyway I\'ve gotta go now',
							'It was a pleasure chat with you',
							'Time to make a new codepen', 'Bye', ':)' ]*/

					function onMessage(evt) {
						var str = evt.data;
						appendMessage(str);
					}

					$('.button').click(function() {
						$('.menu .items span').toggleClass('active');
						$('.menu .button').toggleClass('active');
					});

				});