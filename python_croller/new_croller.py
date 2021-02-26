from selenium import webdriver
import time

web_browser = webdriver.Chrome('./chromedriver.exe')

BASE_URL = 'https://jisaku.com/parts/'


class ProductsInfo:
    __type_name = ''
    __list = []
    __target_url_base = ''
    __page = 0

    def __init__(self, type_name, max_page):
        self.__type_name = type_name
        self.__target_url_base = BASE_URL + type_name + '/search'
        self.__page = max_page + 1
        self.__list = []

    def add_product(self, product):
        self.__list.append(product)

    def save_to_csv(self):
        file = open(self.__type_name + ".csv", "w", encoding="UTF-8")
        for s in self.__list:
            file.write(s[0] + "," + s[1] + "," + s[2] + "\n")

        file.close()

    def get_url_list(self):
        url_list = []
        for i in range(self.__page):
            if i == 0:
                continue

            if i == 1:
                url_list.append(self.__target_url_base)
            else:
                url_list.append(self.__target_url_base + "?page=" + str(i))
        return url_list

    def get_type(self):
        return self.__type_name


PRODUCT_ELEM = 'jisaku-668zgm'

NAME_INFO_ELEM = 'jisaku-1rr4qq7'
NAME_A_ELEM = 'jisaku-peplp8'
INFO_ELEM = 'jisaku-3sqlrp'

PRICE_SECTION_ELEM = 'jisaku-5fbbi9'
PRICE_ELEM = 'jisaku-tful5m'

type_list = [['cpu', '14'], ['motherboard', '65'], ['memory', '60'],
             ['ssd', '98'], ['hdd', '18'], ['graphic-card', '69'],
             ['pc-case', '33'], ['power-supply', '23'], ['cpu-cooler', '25'], ['case-fan', '29']]

info_list = []


for type_name in type_list:
    tn = type_name[0]
    tp = type_name[1]
    info_list.append(ProductsInfo(tn, int(tp)))


for info in info_list:
    url_list = info.get_url_list()
    for url in url_list:
        print('-------------------- [GET: ' + url + '] --------------------')
        web_browser.get(url)
        product_elements = web_browser.find_elements_by_class_name(PRODUCT_ELEM)
        for product_element in product_elements:
            name_info_element = product_element.find_element_by_class_name(NAME_INFO_ELEM)
            product_name = name_info_element.find_element_by_class_name(NAME_A_ELEM).text
            product_spec = name_info_element.find_element_by_class_name(INFO_ELEM).text

            price_element = product_element.find_element_by_class_name(PRICE_SECTION_ELEM)
            price = price_element.find_element_by_class_name(PRICE_ELEM).text

            price = price.replace(",", "").replace("¥", "")

            print(product_name + '(' + price + '): ' + product_spec)
            product_info = [product_name, product_spec, price]
            info.add_product(product_info)
        time.sleep(1)

    print('-------------------- [SAVE TO CSV: ' + info.get_type() + '.csv] --------------------')
    info.save_to_csv()

web_browser.close()
