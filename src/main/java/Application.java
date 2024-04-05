import data.Customer;
import data.Order;
import data.Product;
import repository.customerRepo.MemoryCustomerRepository;
import service.CustomerService;
import service.OrderService;
import service.ProductService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Application {

    //Fields, three service
    private CustomerService customerService;
    private OrderService orderService;
    private ProductService productService;

    //Constructor & Setup
    public Application() {
        // 아직 서비스 객체 싱글톤 아님.
        this.customerService =new CustomerService();
        this.orderService = new OrderService();
        this.productService = new ProductService();
    }

    //main menu method -> interface to user console
    public void menu(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println(NEWLINE_TEN);
            System.out.print(MENU_ROOT);
            int choice = scanner.nextInt();
            switch (choice){
                //1. 고객 관리
                case 1://1-1
                    customerMenu: while(true){
                        System.out.println(NEWLINE_TEN);
                        System.out.print(MENU_CUSTOMER);
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        switch (choice){
                            // 고객 등록
                            case 1:
                                // 여기서 회원 정보를 입력한다
                                System.out.println(NEWLINE_TEN);
                                System.out.print(MENU_CUSTOMER_ADD);
                                try{
                                    Customer newCustomer = inputCustomer();
                                    //저장소에 저장해야 됨
                                    // Service 넘겨서 bool 받고 출력(됐는지 안됐는지)
                                    // 1. 입력이 됐다, 2. 입력이 안됐다( 중복된 값이 있다)
                                    boolean check=customerService.inputCustomer(newCustomer);
                                    if(check)
                                    {
                                        System.out.println("정상적으로 입력 됐습니다");
                                    }
                                    else System.out.println("id값입니다");
                                }catch (InputMismatchException e){
                                    System.out.println(ERROR_WRONG_INPUT);
                                    break;
                                }
                                break;

                            // 고객 삭제
                            case 2:
                                // id값을 기준으로 service에 넘겨준다
                                System.out.println(NEWLINE_TEN);
                                System.out.print(MENU_CUSTOMER_DELETE);
                                //입력을 잘 했다는 가정으로
                                //1. 입력된 정보가 삭제 됐습니다 2. 입력된 정보가 없어 삭제가 안됐습니다

                                long id=scanner.nextLong();
                                scanner.nextLine();
                                boolean check=customerService.deleteCustomer(id);

                                if(check)
                                    System.out.println("삭제가 완료됐습니다");
                                else System.out.println("입력한 정보가 없어 삭제가 안됐습니다");

                                break;
                            // 고객 조회
                            case 3:
                                //id값을 기준으로 조회한다, 전체 조회를 한다
                                System.out.println(NEWLINE_TEN);
                                System.out.print(MENU_CUSTOMER_FIND);

                                choice=scanner.nextInt();
                                scanner.nextLine();

                                switch (choice)
                                {
                                    case 1: //name으로 검색

                                        System.out.printf(MENU_CUSTOMER_FIND_NAME);
                                        String name=scanner.next();// 이름 입력
                                        Customer customer=customerService.findByName(name);
                                        if(customer!=null)
                                            System.out.println(customer.toString());
                                        else System.out.println("일치하는 정보가 없습니다");
                                        break;

                                    case 2: //id로 검색

                                        System.out.println(MENU_CUSTOMER_FIND_ID);
                                        id=scanner.nextLong();
                                        scanner.nextLine();

                                        customer=customerService.findById(id);

                                        if(customer!=null)
                                            System.out.println(customer.toString());
                                        else System.out.println("일치하는 정보가 없습니다");
                                        // 검색, 메소드 -> 출력
                                        break;

                                    case 3:
                                        //일단 전체조회로 했다
                                        // 생일로 검색(MM으로 검색해야 겟지??)//----------이건 고민좀 하자
                                        System.out.println("전체 검색");
                                        List<Customer>customers =customerService.findAll();
                                        if(customers!=null)
                                        {
                                            for(Customer customer1:customers)
                                            {
                                                System.out.println(customer1.toString());
                                            }
                                        }
                                        else System.out.println("등록된 회원이 없습니다");


                                        // 검색, 메소드 -> 출력
                                        break;
                                    case 4:
                                        System.out.println("생일검색");
                                        System.out.println(MENU_CUSTOMER_FIND_BIRTHDAY);
                                        String mmdd=scanner.nextLine();
                                        customer=customerService.findByBirth(mmdd);
                                        if(customer==null)
                                        {
                                            System.out.println("조건에 맞는 회원이 없습니다");
                                        }
                                        else System.out.println(customer.toString());
                                }
                                break;
                            // 뒤로 가기
                            case 0: break customerMenu;
                        }
                    }
                    break;

                //2. 주문 관리
                case 2:
                    orderMenu : while(true){
                        System.out.println(NEWLINE_TEN);
                        System.out.print(MENU_ORDER);
                        choice = scanner.nextInt();
                        switch (choice){
                            // 주문 등록
                            case 1:
                                System.out.println("Oredr Add Entered!"); //테스트용 출력 코드
                                System.out.println(NEWLINE_TEN);
                                System.out.print(MENU_ORDER_ADD);
                                System.out.print("Customer ID: ");
                                long customerId = scanner.nextLong();
                                Customer foundCustomer = customerService.findById(customerId);
                                System.out.print("Peoduct ID: ");
                                long productId = scanner.nextLong();
                                Product foundProduct = productService.findById(productId);
                                System.out.print("Quantity: ");
                                int quantity = scanner.nextInt();
                                String deliverAddr = foundCustomer.getAddress();
                                if(orderService.saveOrder(new Order(foundCustomer, foundProduct, quantity, deliverAddr,0))){
                                    System.out.println("Save Order Complete!");
                                }
                                else{
                                    System.out.println("Save Order Failed!");
                                }
                                break;
                            // 주문 삭제
                            case 2:
                                System.out.println("Order Delete Entered!"); //테스트용 출력 코드
                                System.out.println(NEWLINE_TEN);
                                System.out.print(MENU_ORDER_DELETE);
                                System.out.print("Order ID: ");
                                long orderId = scanner.nextInt();
                                if(orderService.deleteOrder(orderId)){System.out.println("Delete Order Complete!");}
                                else{System.out.println("Delete Order Failed!");}
                                break;
                            // 주문 조회
                            case 3:
                                System.out.println("Order Find Entered!"); //테스트용 출력 코드
                                System.out.println(NEWLINE_TEN);
                                System.out.print(MENU_ORDER_FIND);
                                choice= scanner.nextInt();
                                switch (choice){
                                    case 1:
                                        System.out.print("Enter order ID: ");
                                        orderId = scanner.nextLong();
                                        System.out.println(orderService.findByOrderId(orderId));
                                        break;
                                    case 2:
                                        System.out.print("Enter customer ID: ");
                                        customerId = scanner.nextLong();
                                        for(Order order : orderService.findByCustomer(customerService.findById(customerId))){
                                            System.out.println(order);
                                        }
                                        break;
                                    case 3:
                                        System.out.print("Enter product ID: ");
                                        productId = scanner.nextLong();
                                        for(Order order : orderService.findByProduct(productService.findById(productId))){
                                            System.out.println(order);
                                        }
                                        break;
                                    case 4:
                                        for(Order order : orderService.findOrderAll()){
                                            System.out.println(order);
                                        }
                                        break;
                                    default:
                                        System.out.println(ERROR_WRONG_INPUT);
                                        break;
                                }
                                break;
                            // 뒤로 가기
                            case 0: break orderMenu;
                        }
                    }
                    break;

                //3. 상품 관리
                case 3:
                    productMenu: while(true){
                        System.out.println(NEWLINE_TEN);
                        System.out.println(MENU_PRODUCT);
                        choice = scanner.nextInt();
                        switch (choice){
                            // 상품 등록
                            //
                            case 1:
                                //System.out.println("Product Add Entered!"); //테스트용 출력 코드
                                System.out.println(NEWLINE_TEN);
                                System.out.print(MENU_PRODUCT_ADD);
                                //input
                                System.out.printf("상품코드:");
                                long id= scanner.nextLong();
                                scanner.nextLine();
                                System.out.printf("상품명:");
                                String name=scanner.nextLine();

                                System.out.printf("가격:");
                                int price=scanner.nextInt();

                                System.out.printf("재고:");
                                int stock =scanner.nextInt();
                                scanner.nextLine();
                                System.out.printf("카테고리:");
                                String category=scanner.nextLine();
                                boolean result = productService.saveProduct(id,name, price, stock, category);
                                break;
                            // 상품 삭제
                            case 2:
                                //System.out.println("Product Delete Entered!"); //테스트용 출력 코드
                                System.out.println(NEWLINE_TEN);
                                System.out.print(MENU_PRODUCT_DELETE);
                                System.out.print("상품코드: ");
                                int productID = scanner.nextInt();
                                boolean deleteResult = productService.deleteProduct(productID);
                                if(deleteResult) System.out.println("상품이 정상적으로 삭제되었습니다.");
                                else System.out.println("상품이 존재하지 않습니다.");
                                break;
                            // 상품 조회
                            //
                            case 3:
                                //System.out.println("Product Find Entered!"); //테스트용 출력 코드
                                System.out.println(NEWLINE_TEN);
                                System.out.print(MENU_PRODUCT_FIND);
                                choice = scanner.nextInt();
                                switch (choice){
                                    case 1:
                                        Product foundProduct=null;
                                        scanner.nextLine();
                                        System.out.print("상품명 입력: ");
                                        String productName = scanner.nextLine();
                                        foundProduct = productService.findByName(productName);
                                        if(foundProduct!=null){
                                            System.out.println(foundProduct);
                                        }
                                        else{
                                            System.out.println("상품이 존재하지 않습니다.");
                                        }
                                        break;
                                    case 2:
                                        System.out.print("상품코드 입력: ");
                                        int productId = scanner.nextInt();
                                        foundProduct = productService.findById(productId);
                                        if(foundProduct!=null){
                                            System.out.println(foundProduct);
                                        }
                                        else{
                                            System.out.println("상품이 존재하지 않습니다.");
                                        }
                                        break;
                                    case 0: break;
                                    default:
                                        System.out.println(ERROR_WRONG_INPUT);
                                }
                                break;
                            // 뒤로 가기
                            case 0: break productMenu; // 감사합니다 ㅎㅎ
                        }
                    }
                    break;

                //4. 프로그램 종료
                case 0:
                    System.out.println(TERMINATE_INFO);
                    return;
                default:
                    System.out.println(ERROR_WRONG_INPUT);
                    break;
            }
        }
    }

    // Application's method for surveying and making instance of Customer
    public Customer inputCustomer() throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        //입력 예시를 보여주고
        System.out.print("id: ");//따로 메소드 설정을 고민
        long id = scanner.nextLong();
        scanner.nextLine();

        System.out.print("이름: ");
        String name = scanner.nextLine();

        System.out.print("나이: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("전화번호: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("주소: ");
        String address = scanner.nextLine();

        System.out.print("생년월일(YYYYMMDD): ");
        //TODO 생일 입력받아 LocalDate 객체로 만들기
        String birth = scanner.nextLine();// string -> LocalDate 이거 찾아서 작업해야됨

        LocalDate birthday = LocalDate.parse(birth, DateTimeFormatter.BASIC_ISO_DATE); // 다시 찾아봐
        //System.out.println("생일값 : "+birthday);

        System.out.print("등급: "); // enum바꿀예정
        String tier=scanner.nextLine();
        return new Customer(id, name, age, phoneNumber, address, birthday, tier);
    }
    /*
    public Order inputOrder() throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Customer id: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        System.out.print("product name: ");
        String name = scanner.nextLine();
        System.out.print("quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("delivery address: ");
        String deliveryAddr = scanner.nextLine();
        System.out.print("discount: ");
        int discount = scanner.nextInt();
        return new Order()

    }
     */




    //=============String constants for menu===============

    //root menu
    static final String MENU_ROOT = """
            ========================<MAIN>
            1. 고객 관리
            2. 주문 관리
            3. 상품 관리
            0. 프로그램 종료
            ==============================
            ENTER: """;

    //customer menu
    static final String MENU_CUSTOMER= """
            ====================<CUSTOMER>
            1. 고객 등록
            2. 고객 삭제
            3. 고객 조회
            0. 뒤로 가기
            ==============================
            ENTER: """;
    static final String MENU_CUSTOMER_ADD = """
            ==============================
                       <고객 등록>
            """;
    static final String MENU_CUSTOMER_DELETE = """
            ==============================
                       <고객 삭제>
            """;
    static final String MENU_CUSTOMER_FIND = """
            ==============================
                       <고객 조회>
            1. 이름으로 조회
            2. ID로 조회
            3. 전체 조회
            4. 생일자 조회
            0. 뒤로 가기
            ==============================
            ENTER: """;
    static final String MENU_CUSTOMER_FIND_NAME= """
            ==============================
                       <고객 조회>
            이름: """;
    static final String MENU_CUSTOMER_FIND_ID= """
            ==============================
                       <고객 조회>
            ID: """;

    //TODO
    static final String MENU_CUSTOMER_FIND_BIRTHDAY="";

    //order menu
    static final String MENU_ORDER = """
            =======================<ORDER>
            1. 주문 등록
            2. 주문 삭제
            3. 주문 조회
            0. 뒤로 가기
            ==============================
            ENTER: """;
    static final String MENU_ORDER_ADD = """
            ==============================
                       <주문 등록>
            """;
    static final String MENU_ORDER_DELETE = """
            ==============================
                       <주문 삭제>
            """;
    static final String MENU_ORDER_FIND = """
            ==============================
                       <주문 조회>
            1. 주문 ID로 조회
            2. 고객 ID로 조회
            3. 상품 ID로 조회
            4. 전체 조회
            0. 뒤로 가기
            ==============================
            ENTER: """;
    //product menu
    static final String MENU_PRODUCT= """
            =====================<PRODUCT>
            1. 상품 등록
            2. 상품 삭제
            3. 상품 조회
            0. 뒤로 가기
            ==============================
            ENTER: """;
    static final String MENU_PRODUCT_ADD = """
            ==============================
                       <상품 등록>
            """;
    static final String MENU_PRODUCT_DELETE = """
            ==============================
                       <상품 삭제>
            """;//id 기준으로 삭제
    static final String MENU_PRODUCT_FIND = """
            ==============================
                       <상품 조회>
            1. 이름으로 조회
            2. ID로 조회
            ==============================
            ENTER: """;// 상품 고유아이디, 상품 명

    //terminate info
    static final String TERMINATE_INFO = """
            ***********TERMINATE***********
            .......................GOOD BYE
            """;
    static final String ERROR_WRONG_INPUT= """
            유효하지 않은 입력입니다!
            """;

    static final String NEWLINE_TEN = "\n\n\n\n\n\n\n\n\n\n";

    //=============String constants for menu===============
}
