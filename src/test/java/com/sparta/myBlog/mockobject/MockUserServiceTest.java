package com.sparta.myBlog.mockobject;

import com.sparta.myBlog.Validator.SignUpValidator;
import com.sparta.myBlog.dto.SignupRequestDto;
import com.sparta.myBlog.models.User;
import com.sparta.myBlog.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class MockUserServiceTest {

    @Mock
    UserRepository userRepository;

        @Nested
        @DisplayName("회원가입 테스트 객체 생성")
        class CreateUserDto {

            private String username;
            private String password;
            private String password2;
            private String email;

            @Nested
            @DisplayName("성공")
            class Success{

                @Test
                @DisplayName("성공 케이스1")
                void registerUser(){

                    username = "qwer";
                    password = "1234";
                    password2 = "1234";
                    email = "1234@naver.com";

                    SignupRequestDto requestDto = new SignupRequestDto(
                            username,
                            password,
                            password2,
                            email
                    );
                    SignUpValidator suv = new SignUpValidator(userRepository);
                    assertEquals("회원가입 성공", suv.getValidMessageTest(requestDto));
                    User user = new User(requestDto.getUsername(), requestDto.getPassword(), requestDto.getEmail());
                    userRepository.save(user);
                }

                @Test
                @DisplayName("성공 케이스2")
                void registerUser2(){

                    username = "qwer1234";
                    password = "1234";
                    password2 = "1234";
                    email = "1234@naver.com";

                    SignupRequestDto requestDto = new SignupRequestDto(
                            username,
                            password,
                            password2,
                            email
                    );
                    SignUpValidator suv = new SignUpValidator(userRepository);
                    assertEquals("회원가입 성공", suv.getValidMessageTest(requestDto));
                    User user = new User(requestDto.getUsername(), requestDto.getPassword(), requestDto.getEmail());
                    userRepository.save(user);
                }
            }
            @Nested
            @DisplayName("실패")
            class Failure {

                @Nested
                @DisplayName("아이디 확인")
                class UserId{

                    @Test
                    @DisplayName("아이디 최소 3글자 이상")
                    void UserIdFail1(){

                        username = "qw";
                        password = "1234";
                        password2 = "1234";
                        email = "1234@naver.com";

                        SignupRequestDto requestDto = new SignupRequestDto(
                                username,
                                password,
                                password2,
                                email
                        );
                        SignUpValidator suv = new SignUpValidator(userRepository);
                        assertEquals("아이디를 확인하세요.", suv.getValidMessageTest(requestDto));
                    }

                    @Test
                    @DisplayName("아이디 알파벳 대소문자, 숫자 이외의 값 입력")
                    void UserIdFail2(){

                        username = "qw가1234";
                        password = "1234";
                        password2 = "1234";
                        email = "1234@naver.com";

                        SignupRequestDto requestDto = new SignupRequestDto(
                                username,
                                password,
                                password2,
                                email
                        );
                        SignUpValidator suv = new SignUpValidator(userRepository);
                        assertEquals("아이디를 확인하세요.", suv.getValidMessageTest(requestDto));

                    }

                    @Test
                    @DisplayName("아이디 중복확인 1")
                    void UserDuplication1() {

                        MockUserRepository mockUserRepository = new MockUserRepository();

                        username = "user1";
                        password = "1234";
                        password2 = "1234";
                        email = "1234@naver.com";

                        SignupRequestDto requestDto = new SignupRequestDto(
                                username,
                                password,
                                password2,
                                email
                        );
                        User user = new User(requestDto.getUsername(), requestDto.getPassword(), requestDto.getEmail());
                        mockUserRepository.save(user);

                        username = "user1";
                        password = "1234";
                        password2 = "1234";
                        email = "12345@naver.com";

                        requestDto = new SignupRequestDto(
                                username,
                                password,
                                password2,
                                email
                        );
                        assertTrue(mockUserRepository.existsByUsername(requestDto.getUsername()));
                    }

                    @Test
                    @DisplayName("아이디 중복확인 2")
                    void UserDuplication2() {

                        MockUserRepository mockUserRepository = new MockUserRepository();

                        username = "user2";
                        password = "1234";
                        password2 = "1234";
                        email = "1234@naver.com";

                        SignupRequestDto requestDto = new SignupRequestDto(
                                username,
                                password,
                                password2,
                                email
                        );
                        User user = new User(requestDto.getUsername(), requestDto.getPassword(), requestDto.getEmail());
                        mockUserRepository.save(user);

                        username = "user2";
                        password = "1234";
                        password2 = "1234";
                        email = "12345@naver.com";

                        requestDto = new SignupRequestDto(
                                username,
                                password,
                                password2,
                                email
                        );
                        assertTrue(mockUserRepository.existsByUsername(requestDto.getUsername()));
                    }
                }

                @Nested
                @DisplayName("비밀번호 확인")
                class UserPassword{

                    @Test
                    @DisplayName("비밀번호 4자 이상")
                    void UserPasswordLength() {

                        username = "qwer";
                        password = "123";
                        password2 = "123";
                        email = "1234@naver.com";

                        SignupRequestDto requestDto = new SignupRequestDto(
                                username,
                                password,
                                password2,
                                email
                        );
                        SignUpValidator suv = new SignUpValidator(userRepository);

                        assertEquals("비밀번호를 확인하세요.", suv.getValidMessageTest(requestDto));
                    }

                    @Test
                    @DisplayName("비밀번호가 아이디 포함확인")
                    void UserPasswordContainId() {

                        username = "qwer";
                        password = "qwer";
                        password2 = "qwer";
                        email = "1234@naver.com";

                        SignupRequestDto requestDto = new SignupRequestDto(
                                username,
                                password,
                                password2,
                                email
                        );
                        SignUpValidator suv = new SignUpValidator(userRepository);

                        assertEquals("비밀번호는 닉네임을 포함할 수 없습니다.", suv.getValidMessageTest(requestDto));
                    }

                    @Test
                    @DisplayName("비밀번호 일치 확인")
                    void UserPasswordCorrect() {

                        username = "qwer";
                        password = "1234";
                        password2 = "12345";
                        email = "1234@naver.com";

                        SignupRequestDto requestDto = new SignupRequestDto(
                                username,
                                password,
                                password2,
                                email
                        );
                        SignUpValidator suv = new SignUpValidator(userRepository);

                        assertEquals("비밀번호가 일치하지 않습니다.", suv.getValidMessageTest(requestDto));
                    }
                }
            }
        }
}
